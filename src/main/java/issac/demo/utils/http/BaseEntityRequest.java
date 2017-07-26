package issac.demo.utils.http;

import org.apache.http.HttpEntity;

public abstract class BaseEntityRequest extends Request {

    public BaseEntityRequest(String url, RequestMethod method) throws MethodNotSupportException {
        super(url, method);
    }

    public BaseEntityRequest(String url) throws MethodNotSupportException {
        super(url);
    }


    /**
     * Get HttpEntity about request body
     * @return HttpEntity
     */
    public abstract HttpEntity getEntity();
}
