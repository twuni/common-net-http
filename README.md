Overview
--------

Let's face it. The native Java API is starting to show its age. It has been for awhile.
The community has learned so much over the years, yet we are still using the same awkward
servlet API for our web applications. It's time for a new Java web server that isn't based
on the servlet API.

This project aims to provide a lean interface with which your web applications can be shared
with the world. Dependency injection and concise design are the two guiding tenets.


Sample Code
-----------

Here is a simple Hello World example:

	RequestMapping mapping = new RequestMapping();
	mapping.map( Method.GET, "/hello", new StaticResponder( Status.OK, "Hello, world!" ) );
	new Server( 8080, mapping ).start();

This will start an HTTP server on port `8080` which responds to a `GET` request for `/hello`
by answering `Hello, world!`. It will respond to all other requests with an HTTP 404 or 405 error.
Let's dissect this code line-by-line to see what is happening here.

	RequestMapping mapping = new RequestMapping();

This simply initializes a new request mapping. Not much to see here.

	mapping.map( Method.GET, "/hello", new StaticResponder( Status.OK, "Hello, world!" ) );

Hey, this isn't so bad, either! Here, we are mapping the `GET` method and the URI pattern `/hello`
to a request handler. In this case, we are just using a static response every time, so we're using
the `StaticResponder` class. We want the request to succeed, so we use `Status.OK`. Finally, we say
that we want our static response to be *Hello, world!*.

	new Server( 8080, mapping ).start();

Here, we are saying that we want to start up a server on port `8080` using the mapping we just put together.
