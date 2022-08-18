<h1 align="center">Cacheable Property of RESTful APIs</h1>
<h2 align="center">What is it?</h2>

The cacheable property means that responses for incoming requests can be cached and sent to the client for subsequent, same requests. For example, a `GET` request for a large amount of data can be processed once, then any subsequent requests, the response will already be there to send to the client and not have to be made again for the duration of the cache. 

This will result in a request that can take 200ms, for example, to be sent, processed, and responded to, to take 200ms once, and then 5ms for any subsequent requests afterwards. This makes the API much more efficient in both time and resources.

`GET` requests should be cacheable and all browsers treat them as such. `POST` requests are not cacheable by default but can be made cacheable by the use of `Expires` and `Cache-Control` headers on the response. `PUT` and `DELETE` are not cacheable methods.<br><br>

<h2 align="center">How?</h2>
As mentioned above, there are a few headers that can be used to control caching behavior.<br><br>

### Expires

The `Expires` header is used to declare a time at which the response's cache will be invalidated and afterwards the response must be processed again by the server.

Example: `Expires: Wed, 3 Aug 2022 10:00:00 GMT`

This shows that the expiry date of the response is at the time specified.

### Cache-Control

The `Cache-Control` header gives some control over how the caching system works with several directives. Some response directives are as follows:

#### max-age=N
This directive shows that the response is fresh, or can be used for subsequent events, for N seconds.

#### s-maxage=N
This directive is similar to max-age except that it is specific to shared caches only and those caches will ignore max-age. Shared caches are shared among multiple clients, whereas private caches are per-client. Browsers will generally use private caches for all requests.

#### no-cache
This is a flag indicating that the response will check with the server per-request if there is any change in the resource being accessed.

#### no-store
This is a flag indicating that this response should not be stored at all, thus preventing any caching behavior.

#### must-revalidate

This is a flag indicating that whenever the response becomes stale that it must be validated again before being used. This is used along with `max-age` and similar directives. This directive is used because HTTP can allow the reuse of stale responses.

#### proxy-revalidate

This is similar to `must-revalidate` except that it is used for private caches.

#### private
This is a flag indicating that the response can only be stored in a private cache. This is for more private cached information like personal cookies and login authorization.

#### public
This is a flag indicating that the response can be stored in public caches.

#### must-understand

This is a flag indicating that that the response should only be stored in the cache if the cache understands the requirements for caching based on status code. This is usually coupled with `no-store` where `no-store` is used as a fallback if the cache does not support `must-understand`. 

#### no-transform
This is a flag indicating that the response should not be transformed during transport. Examples of transforming a response is converting images to reduce transfer size.

#### immutable
This is a flag indicating that the response should not be updated while it is fresh.

#### stale-while-revalidate=N
This is a directive indicating that the response can be used for N seconds while the response is being revalidated by the server once it becomes stale.

#### stale-if-error=N
This is a directive indicating that the response should use a stale response for N seconds if the server reports 5xx status code for stale responses.

### Client directives

There are also directives that the client can use to control caching behavior. They are as follows:

#### no-cache, no-store, max-age=N, no-transform
Same as the response directives.

#### max-stale=N
This directive indicates that the client allows a stored response that is stale in N seconds.

#### min-fresh=N
This directive indicates that the client allows a stored response that is fresh for N seconds.

#### only-if-cached
This is a flag indicating that the client should only obtain an already-cached response. 

### Validators

Responses with `Cache-Control` should also contain a validator. Validators, `ETag` and `Last-Modified` basically provide a way to check if a cache is stale or fresh. These are set in the header of request.

#### ETag

`ETag` is a token that the server will associate to a resource to identify its state over the resource's lifetime. If the resource changes, it gets a new `ETag` token. When a client requests a resource and sends an `ETag` in a `If-None-Match` header, the server will check if the `ETag` matches the requested resource, and if so, it will return a `304 Not Modified` status stating that the resource has not changed and the cached response is still fresh.

Example: `ETag: "4r4nd0m70k3n"`

#### Last-Modified

`Last-Modified` is a Date header that indicated when the response was generated and will determine based on the Date if the response is fresh. This is less accurate than an `ETag` and should only be used as a fallback. 

Example: `Last-Modified: Wed, 3 Aug 2022 10:00:00 GMT`
