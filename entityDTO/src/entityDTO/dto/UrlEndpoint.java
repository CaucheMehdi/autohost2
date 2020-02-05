package entityDTO.dto;

public class UrlEndpoint {

    public static final String MANAGER_GET_RESSOURCE              = "http://127.0.0.1:8890/manager/post/ressource";
    public static final String MANAGER_SEND_INSTANCE_TO_CONFIGURE = "http://127.0.0.1:8890/manager/configure/ressource";

    /* CUSTOMER_APP ENDPOINT */
    public static final String CUSTOMERAPP_ROOT = "http://127.0.0.1:8889";

    public static final String RECEIVE_ORDER_MAPPING  = "/customer/receive/order";
    public static final String POST_RESSOURCE_MAPPING = "/customer/post/ressource";
    public static final String CREATE_MAPPING         = "/customer/create";
    public static final String DELETE_MAPPING         = "/customer/delete";

    public static final String CUSTOMER_RECEIVE_ORDER_ENDPOINT   = CUSTOMERAPP_ROOT + RECEIVE_ORDER_MAPPING;
    public static final String CUSTOMER_CREATE_CUSTOMER_ENDPOINT = CUSTOMERAPP_ROOT + CREATE_MAPPING;
    public static final String CUSTOMER_DELETE_CUSTOMER_ENDPOINT = CUSTOMERAPP_ROOT + DELETE_MAPPING;
    public static final String CUSTOMER_POST_ORDER_ENPOINT       = CUSTOMERAPP_ROOT + POST_RESSOURCE_MAPPING;

    /* rest repo */
    public static final String REST_REPO_ROOT = "http://127.0.0.1:9900";

    public static final String REST_CUSTOMER_SEARCH_BY_EMAIL_MAPPING  = "/customer/searchByEmail?mail=";
    public static final String REST_CUSTOMER_SEARCH_BY_EMAIL_ENDPOINT = REST_REPO_ROOT + REST_CUSTOMER_SEARCH_BY_EMAIL_MAPPING;

    public static final String REST_REPO_RESSOURCE = "http://127.0.0.1:9900/ressource";

    public static final String ORDERER_ORDER_INSTANCE = "http://127.0.0.1:8900/server/order/instance";

}
