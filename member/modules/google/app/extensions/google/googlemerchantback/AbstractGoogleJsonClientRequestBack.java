package extensions.google.googlemerchantback;

import com.google.api.client.googleapis.batch.BatchRequest;
import com.google.api.client.googleapis.batch.json.JsonBatchCallback;
import com.google.api.client.googleapis.json.GoogleJsonErrorContainer;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.UriTemplate;
import com.google.api.client.http.json.JsonHttpContent;

import java.io.IOException;

/**
 * Google JSON request for a {@link AbstractGoogleJsonClient}.
 *
 * <p>
 * Implementation is not thread-safe.
 * </p>
 *
 * @param <T> type of the response
 * @since 1.12
 * @author Yaniv Inbar
 */
public abstract class AbstractGoogleJsonClientRequestBack<T> extends AbstractGoogleClientRequestBack<T> {

  /** POJO that can be serialized into JSON content or {@code null} for none. */
  private final Object jsonContent;

  /**
   * @param abstractGoogleJsonClient Google JSON client
   * @param requestMethod HTTP Method
   * @param uriTemplate URI template for the path relative to the base URL. If it starts with a "/"
   *        the base path from the base URL will be stripped out. The URI template can also be a
   *        full URL. URI template expansion is done using
   *        {@link UriTemplate#expand(String, String, Object, boolean)}
   * @param jsonContent POJO that can be serialized into JSON content or {@code null} for none
   * @param responseClass response class to parse into
   */
  protected AbstractGoogleJsonClientRequestBack(AbstractGoogleJsonClientBack abstractGoogleJsonClient,
      String requestMethod, String uriTemplate, Object jsonContent, Class<T> responseClass) {
    super(abstractGoogleJsonClient, requestMethod, uriTemplate, jsonContent == null ? null
        : new JsonHttpContent(abstractGoogleJsonClient.getJsonFactory(), jsonContent)
            .setWrapperKey(abstractGoogleJsonClient.getObjectParser().getWrapperKeys().isEmpty()
                ? null : "data"), responseClass);
    this.jsonContent = jsonContent;
  }

  @Override
  public AbstractGoogleJsonClientBack getAbstractGoogleClient() {
    return (AbstractGoogleJsonClientBack) super.getAbstractGoogleClient();
  }

  @Override
  public AbstractGoogleJsonClientRequestBack<T> setDisableGZipContent(boolean disableGZipContent) {
    return (AbstractGoogleJsonClientRequestBack<T>) super.setDisableGZipContent(disableGZipContent);
  }

  @Override
  public AbstractGoogleJsonClientRequestBack<T> setRequestHeaders(HttpHeaders headers) {
    return (AbstractGoogleJsonClientRequestBack<T>) super.setRequestHeaders(headers);
  }

  /**
   * Queues the request into the specified batch request container.
   *
   * <p>
   * Batched requests are then executed when {@link BatchRequest#execute()} is called.
   * </p>
   * <p>
   * Example usage:
   * </p>
   *
   * <pre>
     request.queue(batchRequest, new JsonBatchCallback&lt;SomeResponseType&gt;() {

       public void onSuccess(SomeResponseType content, HttpHeaders responseHeaders) {
         log("Success");
       }

       public void onFailure(GoogleJsonError e, HttpHeaders responseHeaders) {
         log(e.getMessage());
       }
     });
   * </pre>
   *
   *
   * @param batchRequest batch request container
   * @param callback batch callback
   */
  public final void queue(BatchRequest batchRequest, JsonBatchCallback<T> callback)
      throws IOException {
    super.queue(batchRequest, GoogleJsonErrorContainer.class, callback);
  }

  @Override
  protected GoogleJsonResponseException newExceptionOnError(HttpResponse response) {
    return GoogleJsonResponseException.from(getAbstractGoogleClient().getJsonFactory(), response);
  }

  /** Returns POJO that can be serialized into JSON content or {@code null} for none. */
  public Object getJsonContent() {
    return jsonContent;
  }

  @Override
  public AbstractGoogleJsonClientRequestBack<T> set(String fieldName, Object value) {
    return (AbstractGoogleJsonClientRequestBack<T>) super.set(fieldName, value);
  }
}
