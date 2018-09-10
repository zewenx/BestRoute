/**
  * Copyright 2018 bejson.com 
  */
package com.francis.bestroute.vo;
import java.util.List;

/**
 * Auto-generated: 2018-08-31 16:48:22
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JsonRootBean {

    private List<String> destination_addresses;
    private List<String> origin_addresses;
    private List<Rows> rows;
    private String status;
    public void setDestination_addresses(List<String> destination_addresses) {
         this.destination_addresses = destination_addresses;
     }
     public List<String> getDestination_addresses() {
         return destination_addresses;
     }

    public void setOrigin_addresses(List<String> origin_addresses) {
         this.origin_addresses = origin_addresses;
     }
     public List<String> getOrigin_addresses() {
         return origin_addresses;
     }

    public void setRows(List<Rows> rows) {
         this.rows = rows;
     }
     public List<Rows> getRows() {
         return rows;
     }

    public void setStatus(String status) {
         this.status = status;
     }
     public String getStatus() {
         return status;
     }

}