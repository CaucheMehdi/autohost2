package entityDTO.dto;

public class UrlEndpoint {

    public static final String MANAGER_GET_RESSOURCE              = "http://127.0.0.1:8890/manager/post/ressource";
    public static final String MANAGER_SEND_INSTANCE_TO_CONFIGURE = "http://127.0.0.1:8890/manager/configure/ressource";
    
    /* send an order to */
    public static final String CUSTOMER_POST_ORDER                = "http://127.0.0.1:8889/customer/post/ressource";
    public static final String CUSTOMER_RECEIVE_ORDER             = "http://127.0.0.1:8889/customer/receive/order";
    public static final String CUSTOMER_CREATE_CUSTOMER           = "http://127.0.0.1:8889/customer/create";
    public static final String CUSTOMER_DELETE_CUSTOMER           = "http://127.0.0.1:8889/customer/delete";
    
    
    /* rest repo */
    public static final String REST_REPO          				  = "http://127.0.0.1:9900";
    public static final String REST_REPO_CUSTOMER  				  = "http://127.0.0.1:9900/customer";
    public static final String REST_REPO_RESSOURCE  			  = "http://127.0.0.1:9900/ressource";


    public static final String ORDERER_ORDER_INSTANCE = "http://127.0.0.1:8900/server/order/instance";

}
