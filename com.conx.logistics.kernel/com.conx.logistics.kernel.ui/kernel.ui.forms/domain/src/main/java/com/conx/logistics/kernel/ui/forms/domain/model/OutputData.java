/*

 *



 *

 *





 */
package com.conx.logistics.kernel.ui.forms.domain.model;





public class OutputData extends Data {

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && obj instanceof OutputData;
    }
    
    @Override
    public int hashCode() {
        return super.hashCode() * 37 + 21951;
    }
}
