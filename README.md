Overview
--------

Let's face it. The native Java API is starting to show its age. It has been for awhile.
The community has learned so much over the years, yet we are still using the same awkward
servlet API for our web applications. It's time for a new Java web server that isn't based
on the servlet API.

This project aims to provide a lean interface with which your web applications can be shared
with the world.


Getting Started
---------------

Let's start with a simple *Hello World* example:

	new HTTPServer( 8080, "Hello, world!" ).start();

Loading `http://localhost:8080/` in a browser will give you this:

	Hello, world!

In fact, loading `http://localhost:8080/foo` would also give you this response.


Routes
------

Adding distinct routes is easy:

	Routes routes = new Routes();
	
	routes.add( "/hello", "Hello, world!" );
	routes.add( "/goodbye", "Goodbye, my love." );
	
	new HTTPServer( 8080, routes ).start();

You can also specify the HTTP method:

	routes.add( Method.POST, "/user", "You created a new user. Congratulations." );


Responders
----------

Routes are mapped to implementations of the `Responder` interface, which contain discrete
pieces of application logic related to that route. For example, you might have
a `CreateUser` responder that looks something like this:

	@RespondsTo( method = Method.POST, value = "/users" )
	public class CreateUser implements Responder {
	
		private final Adapter<Request, User> adapter = new CreateUserRequestAdapter();
	
		@Override
		public Response respondTo( Request request ) {
			User user = adapter.adapt( request );
			user.save();
			return new Response( user.toJSON() );
		}
		
	}

Since this class uses the `@RespondsTo` annotation, you can tell your routes to scan this class:

	routes.scan( CreateUser.class );

...or you could configure the route explicitly:

	routes.add( Method.POST, "/users", new CreateUser() );


Limitations
-----------

This is not a full-service application development framework. For all but the most simple web
applications, an MVC framework should probably live between this and your application logic.
