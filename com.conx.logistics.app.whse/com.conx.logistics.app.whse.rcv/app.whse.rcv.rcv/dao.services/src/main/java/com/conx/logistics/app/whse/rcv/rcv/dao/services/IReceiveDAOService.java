package com.conx.logistics.app.whse.rcv.rcv.dao.services;

import java.util.List;
import java.util.Set;

import com.conx.logistics.app.whse.rcv.asn.domain.ASN;
import com.conx.logistics.app.whse.rcv.rcv.domain.Receive;


public interface IReceiveDAOService {
	public List<Receive> getAll();

	public Receive add(Receive record);
	
	public Receive process(ASN asn);
	
	public void delete(Receive record);

	public Receive update(Receive record);

	public Receive get(long id);
}
