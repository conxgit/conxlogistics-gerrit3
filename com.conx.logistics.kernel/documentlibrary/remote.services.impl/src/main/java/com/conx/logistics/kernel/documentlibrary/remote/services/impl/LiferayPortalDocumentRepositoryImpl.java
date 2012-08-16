package com.conx.logistics.kernel.documentlibrary.remote.services.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conx.logistics.common.utils.StringUtil;
import com.conx.logistics.kernel.documentlibrary.remote.services.IRemoteDocumentRepository;
import com.conx.logistics.kernel.documentlibrary.remote.services.impl.liferay.LiferayPortalDocumentRepositoryTests;
import com.conx.logistics.kernel.metamodel.domain.EntityType;
import com.conx.logistics.mdm.dao.services.documentlibrary.IFolderDAOService;
import com.conx.logistics.mdm.domain.documentlibrary.FileEntry;
import com.conx.logistics.mdm.domain.documentlibrary.Folder;

import flexjson.JSONDeserializer;

@Transactional
@Service
public class LiferayPortalDocumentRepositoryImpl implements
		IRemoteDocumentRepository {
	
	static public final String CONXDOCREPO_SERVER_HOSTNAME = "conxdocrepo.server.hostname";//localhost
	static public final String CONXDOCREPO_SERVER_PORT = "conxdocrepo.server.port";//8080
	static public final String CONXDOCREPO_REPOSITORY_ID = "conxdocrepo.repository.id";//10180
	static public final String CONXDOCREPO_REPOSITORY_COMPANYID = "conxdocrepo.repository.companyid";//10154
	static public final String CONXDOCREPO_REPOSITORY_CONXLOGISTICS_FOLDERID = "conxdocrepo.repository.conxlogistics.folderid";//10644
	static public final String CONXDOCREPO_USER_EMAIL = "conxdocrepo.user.email";//test@liferay.com
	static public final String CONXDOCREPO_USER_PASSWORD = "conxdocrepo.user.password";//test
	static public final String CONXDOCREPO_USER_GROUP_ID = "conxdocrepo.user.group.id";//10180
	
	private Properties liferayProperties = new Properties();

	private BasicAuthCache authCache;
	private DefaultHttpClient httpclient;
	private HttpHost targetHost;
	private String repositoryId;
	private String companyId;
	private String conxlogiFolderId;
	private String loginEmail;
	private String loginPassword;
	private String hostname;
	private String port;
	private String loginGroupId;
	
	
	@Autowired
	private IFolderDAOService folderDAOService;
	
	@Override
	public void init()
	{
		loadLiferayProperties();
		
		hostname = liferayProperties.getProperty(CONXDOCREPO_SERVER_HOSTNAME);
		port = liferayProperties.getProperty(CONXDOCREPO_SERVER_PORT);
		repositoryId = liferayProperties.getProperty(CONXDOCREPO_REPOSITORY_ID);
		companyId = liferayProperties.getProperty(CONXDOCREPO_REPOSITORY_COMPANYID);
		conxlogiFolderId = liferayProperties.getProperty(CONXDOCREPO_REPOSITORY_CONXLOGISTICS_FOLDERID);
		loginGroupId = liferayProperties.getProperty(CONXDOCREPO_USER_GROUP_ID);
		loginEmail = liferayProperties.getProperty(CONXDOCREPO_USER_EMAIL);
		loginPassword = liferayProperties.getProperty(CONXDOCREPO_USER_PASSWORD);
		
		targetHost = new HttpHost(hostname, Integer.valueOf(port), "http");
		httpclient = new DefaultHttpClient();
		httpclient.getCredentialsProvider().setCredentials(
				new AuthScope(targetHost.getHostName(), targetHost.getPort()),
				new UsernamePasswordCredentials(loginEmail, loginPassword));

		// Create AuthCache instance
		this.authCache = new BasicAuthCache();
		// Generate BASIC scheme object and add it to the local
		// auth cache
		BasicScheme basicAuth = new BasicScheme();
		authCache.put(targetHost, basicAuth);	
	}

	@Override
	public Boolean isAvailable() throws Exception {
		return getLoginUserId() != null;
	}

	@Override
	public Folder getFolderById(String folderId)  throws Exception {
		// Add AuthCache to the execution context
		BasicHttpContext ctx = new BasicHttpContext();
		ctx.setAttribute(ClientContext.AUTH_CACHE, authCache);
		
		HttpPost post = new HttpPost(
				"/api/secure/jsonws/dlapp/get-folder");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("folderId", folderId));
		//params.add(new BasicNameValuePair("name", "Receive1"));
		//params.add(new BasicNameValuePair("description", "Receive1"));
		

		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
		post.setEntity(entity);
		
		
		HttpResponse resp = httpclient.execute(targetHost, post, ctx);
		System.out.println("getFolderById Status:["+resp.getStatusLine()+"]");
		
		String response = null;
		if(resp.getEntity()!=null) {
		    response = EntityUtils.toString(resp.getEntity());
		}
		System.out.println("getFolderById Res:["+response+"]");
		
		JSONDeserializer<Folder> deserializer = new JSONDeserializer<Folder>();
		Folder fldr = deserializer.deserialize(response,Folder.class);
		
		return fldr;
	}

	@Override
	public Folder getFolderByName(String parentFolderId, String name)  throws Exception{
		// Add AuthCache to the execution context
		BasicHttpContext ctx = new BasicHttpContext();
		ctx.setAttribute(ClientContext.AUTH_CACHE, authCache);
		
		HttpPost post = new HttpPost(
				"/api/secure/jsonws/dlapp/get-folder");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("repositoryId", repositoryId));
		params.add(new BasicNameValuePair("parentFolderId", parentFolderId));
		params.add(new BasicNameValuePair("name", name));
		

		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
		post.setEntity(entity);
		
		
		HttpResponse resp = httpclient.execute(targetHost, post, ctx);
		System.out.println(resp.getStatusLine());
		
		String response = null;
		if(resp.getEntity()!=null) {
		    response = EntityUtils.toString(resp.getEntity());
		}
		System.out.println("getFolderByName Res:["+response+"]");
		
		Folder fldr = null;
		if (!StringUtil.contains(response, "com.liferay.portlet.documentlibrary.NoSuchFolderException",""))
		{
			JSONDeserializer<Folder> deserializer = new JSONDeserializer<Folder>();
			fldr = deserializer.deserialize(response,Folder.class);
		}
			
			
		return fldr;
	}
	
	@Override
	public boolean folderExists(String parentFolderId, String name) throws Exception {
		Folder fldr = getFolderByName(parentFolderId, name);
		return fldr != null;
	}

	@Override
	public void deleteFolderById(String folderId)  throws Exception{
		// Add AuthCache to the execution context
		BasicHttpContext ctx = new BasicHttpContext();
		ctx.setAttribute(ClientContext.AUTH_CACHE, authCache);
		
		HttpPost post = new HttpPost(
				"/api/secure/jsonws/dlapp/delete-folder");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("folderId", folderId));

		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
		post.setEntity(entity);
		
		
		HttpResponse resp = httpclient.execute(targetHost, post, ctx);
		System.out.println(resp.getStatusLine());
		
		String response = null;
		if(resp.getEntity()!=null) {
		    response = EntityUtils.toString(resp.getEntity());
		}
		
		System.out.println("deleteFolderById Res:["+response+"]");
	}

	@Override
	public void deleteFolderByName(String parentFolderId, String name)  throws Exception{
		Folder fdlr = getFolderByName(parentFolderId, name);
		deleteFolderById(Long.toString(fdlr.getFolderId()));
	}

	@Override
	public List<FileEntry> getFileEntries(String folderId)  throws Exception{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileEntry getFileEntryById(String fileEntryId)  throws Exception{
		// Add AuthCache to the execution context
		BasicHttpContext ctx = new BasicHttpContext();
		ctx.setAttribute(ClientContext.AUTH_CACHE, authCache);
		
		HttpPost post = new HttpPost(
				"/api/secure/jsonws/dlapp/get-file-entry");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("fileEntryId", fileEntryId));

		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
		post.setEntity(entity);
		
		
		HttpResponse resp = httpclient.execute(targetHost, post, ctx);
		System.out.println("getFolderById Status:["+resp.getStatusLine()+"]");
		
		String response = null;
		if(resp.getEntity()!=null) {
		    response = EntityUtils.toString(resp.getEntity());
		}
		System.out.println("getFileEntry Res:["+response+"]");
		
		JSONDeserializer<FileEntry> deserializer = new JSONDeserializer<FileEntry>();
		FileEntry fe = deserializer.deserialize(response,FileEntry.class);
		
		return fe;
	}

	@Override
	public FileEntry deleteFileEntryById(String folderId, String fileEntryId)  throws Exception{
		// TODO Auto-generated method stub
		return null;
	}

	protected Properties loadLiferayProperties() {
		if (!liferayProperties.isEmpty()) {
			return liferayProperties;
		}
		try {
			liferayProperties.load(LiferayPortalDocumentRepositoryImpl.class
					.getResourceAsStream("/conxdocrepo.properties"));
		} catch (IOException e) {
			throw new RuntimeException(
					"Could not load conxportal.properties", e);
		}

		return liferayProperties;
	}
	
	private String getLoginUserId() throws ParseException, IOException
	{
		// Add AuthCache to the execution context
		BasicHttpContext ctx = new BasicHttpContext();
		ctx.setAttribute(ClientContext.AUTH_CACHE, authCache);
		
		HttpPost post = new HttpPost(
				"/api/secure/jsonws/user/get-user-id-by-email-address");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("companyId", companyId));
		params.add(new BasicNameValuePair("emailAddress", "test@liferay.com"));
	
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
		post.setEntity(entity);
		
		
		HttpResponse resp = httpclient.execute(targetHost, post, ctx);
		System.out.println(resp.getStatusLine());
		
		String response = null;
		if(resp.getEntity()!=null) {
		    response = EntityUtils.toString(resp.getEntity());
		}
		
		return response;
	}


	@Override
	public Folder addFolder(String parentFolderId, String name,
			String description) throws Exception {
		// Add AuthCache to the execution context
		BasicHttpContext ctx = new BasicHttpContext();
		ctx.setAttribute(ClientContext.AUTH_CACHE, authCache);
		
		HttpPost post = new HttpPost(
				"/api/secure/jsonws/dlapp/add-folder");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("repositoryId", repositoryId));
		params.add(new BasicNameValuePair("parentFolderId", conxlogiFolderId));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("description", description));
		

		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
		post.setEntity(entity);
		
		
		HttpResponse resp = httpclient.execute(targetHost, post, ctx);
		System.out.println(resp.getStatusLine());
		
		String response = null;
		if(resp.getEntity()!=null) {
		    response = EntityUtils.toString(resp.getEntity());
		}

		JSONDeserializer<Folder> deserializer = new JSONDeserializer<Folder>();
		Folder fldr = deserializer.deserialize(response,Folder.class);
		
		
		System.out.println("addFolder Res:["+response+"]");
		return fldr;		
	}

	@Override
	public FileEntry addFileEntry(String folderId, String sourceFileName,
			String mimeType, String title, String description, InputStream data, long size)
			throws Exception {
/*		ClientConfig cc = new DefaultClientConfig();
		cc.getClasses().add(MultiPartWriter.class);

		Client client = Client.create(cc);
		client.addFilter(new HTTPBasicAuthFilter(loginEmail, loginPassword));

		WebResource webResource = client.resource(UriBuilder.fromUri("http://localhost:8080/api/secure/jsonws/dlapp/add-file-entry").build());


		FormDataMultiPart form = new FormDataMultiPart();
		form.field("repositoryId", repositoryId);
		form.field("folderId", folderId);
		form.field("sourceFileName", sourceFileName);
		form.field("mimeType",mimeType);
		form.field("title", title);
		form.field("description", description);
		form.field("change Log", "");
		form.field("is", data, MediaType.MULTIPART_FORM_DATA_TYPE);
		form.field("size", "0");
		form.field("serviceContext", "{}");
		
		ClientResponse response = webResource.type(MediaType.MULTIPART_FORM_DATA).post(ClientResponse.class, form);

		System.out.println(response.getEntity(String.class));*/
		
		// Add AuthCache to the execution context
		BasicHttpContext ctx = new BasicHttpContext();
		ctx.setAttribute(ClientContext.AUTH_CACHE, authCache);
		
		HttpPost post = new HttpPost("/api/secure/jsonws/dlapp/add-file-entry");
		MultipartEntity entity = new MultipartEntity(
				HttpMultipartMode.BROWSER_COMPATIBLE);
		
		final URL testfile = LiferayPortalDocumentRepositoryTests.class.getResource("/bol.pdf");
		File file = new File(testfile.toURI());
		
		entity.addPart("repositoryId",new StringBody(repositoryId, Charset.forName("UTF-8")));
		entity.addPart("folderId",new StringBody(folderId, Charset.forName("UTF-8")));
		entity.addPart("sourceFileName",new StringBody(sourceFileName, Charset.forName("UTF-8")));
		entity.addPart("mimeType ", new StringBody(mimeType,Charset.forName("UTF-8")));
		entity.addPart("title", new StringBody(title,Charset.forName("UTF-8")));
		entity.addPart("description", new StringBody(description,Charset.forName("UTF-8")));
		entity.addPart("changeLog", new StringBody("",Charset.forName("UTF-8")));		
		entity.addPart("file", new FileBody(file,mimeType,sourceFileName));
		//entity.addPart("bytes", new ByteArrayBody("Test Content".getBytes(),mimeType,sourceFileName));
		entity.addPart("size", new StringBody("0",Charset.forName("UTF-8")));
		entity.addPart("serviceContext", new StringBody("{}",Charset.forName("UTF-8")));

		post.setEntity(entity);

		HttpResponse resp = httpclient.execute(targetHost, post, ctx);
		System.out.println(resp.getStatusLine());
		
		String response = null;
		if(resp.getEntity()!=null) {
		    response = EntityUtils.toString(resp.getEntity());
		}
		System.out.println("addFileEntry Res:["+response+"]");

		FileEntry fe = null;
		if (!StringUtil.contains(response, "com.liferay.portlet.documentlibrary.NoSuchFileEntryException", ""))
		{
			JSONDeserializer<FileEntry> deserializer = new JSONDeserializer<FileEntry>();
			fe = deserializer.deserialize(response,FileEntry.class);
		}
		return fe;
	}

	@Override
	public String getConxlogiFolderId() {
		return conxlogiFolderId;
	}

	@Override
	public boolean fileEntryExists(String parentFolderId, String title)
			throws Exception {
		FileEntry fe = getFileEntryByTitle(parentFolderId,title);
		return fe != null;
	}

	@Override
	public FileEntry getFileEntryByTitle(String parentFolderId, String title)
			throws Exception {
		// Add AuthCache to the execution context
		BasicHttpContext ctx = new BasicHttpContext();
		ctx.setAttribute(ClientContext.AUTH_CACHE, authCache);
		
		HttpPost post = new HttpPost(
				"/api/secure/jsonws/dlapp/get-file-entry");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("folderId", parentFolderId));
		params.add(new BasicNameValuePair("groupId", loginGroupId));
		params.add(new BasicNameValuePair("title", title));
		

		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
		post.setEntity(entity);
		
		
		HttpResponse resp = httpclient.execute(targetHost, post, ctx);
		System.out.println("getFolderById Status:["+resp.getStatusLine()+"]");
		
		String response = null;
		if(resp.getEntity()!=null) {
		    response = EntityUtils.toString(resp.getEntity());
		}
		System.out.println("getFileEntry Res:["+response+"]");
		
		FileEntry fe = null;
		if (!StringUtil.contains(response, "com.liferay.portlet.documentlibrary.NoSuchFileEntryException", ""))
		{
			JSONDeserializer<FileEntry> deserializer = new JSONDeserializer<FileEntry>();
			fe = deserializer.deserialize(response,FileEntry.class);
		}
		return fe;
	}

	@Override
	public Folder provideFolderForEntity(EntityType entityType, Long entityId) throws Exception {
		String st = entityType.getEntityJavaSimpleType();
		String folderName = st+"-"+entityId;
		
		return provideFolderByJavaTypeName(folderName);
	}

	private Folder provideFolderByJavaTypeName(String folderName)
			throws Exception {
		//-- Create remote
		Folder fldr = addFolder(conxlogiFolderId, folderName, "Attachments for Record["+folderName+"]");
		
		//-- Create local
		fldr.setName(folderName);
		fldr = folderDAOService.provide(fldr);
		return fldr;
	}

	@Override
	public Folder provideFolderForEntity(Class entityJavaClass, Long entityId)  throws Exception  {
		String st = entityJavaClass.getSimpleName();
		String folderName = st+"-"+entityId;
		
		return provideFolderByJavaTypeName(folderName);
	}
}
