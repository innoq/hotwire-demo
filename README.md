# Hotwire DEMO
A simple Spring Boot Application to showcase the integration of [Hotwire](https://hotwire.dev) into a 'normal' Spring Boot Webapplication

## Getting started

1. Build the JS Parts
```
cd src/main/js
npm install
npm run build && npm run deploy
```

2. Run the application
```
./mvnw clean install spring-boot:run
```

Point your browser to [http://localhost:8080](http://localhost:8080)

## under the hood

### JS

The little JS Code that is necessary is built in `src/main/js` using [snowpack](https://snowpack.dev), to add minimal overhead. The necessary call is wired to the `build` target of npm. 
The `deploy` target copies the JS Files (and their dependencies) to the `static` path of the Spring Boot Application from where they are served (Note: since we do this, the path is added to `.gitignore` to make sure we don't check in any stale JS - so all scripts wuold have to be added this way unless you write directly into the page)

In order for the demo to run properly you need to run `npm run build && npm run deploy` at least once, so the necessary files are created.


### Java

The Spring app is mostly plain vanilla Spring Boot/WebMVC with Thymeleaf for rendering templates.


#### Spring Config

There are a few Configuration Files that are necessary to set up the infrastructure:

- `WebSocketConfig.java` – registers our plain vanilla Websocket Handler to the URL expected in the frontend. As we do not want complex integration, we avoid the whole STOMP / SockJS Setup and just do simple Websockets.
- `TaskSchedulerConfiguration.java` – since we use `@Scheduled` in some controllers, but do _not_ use Stomp, we need to provide a custom Setup here, otherwise the Stomp integration fails with a missing bean.

#### other interesting Code

- `MessagesController.java` – handles all the dynamic requests for data in the Turbo-Frames pages. It usually erdirects to the origin page, when not a more detailed response is necessary
- `TurboStreamWebsocketHandler.java` – a minimalistic Websocket Handler that is simply used to send turbo-streams updates to the Client. In a _real_ application you would want to provide access to this component, so other Parts of the application could provide their content to this. Also note, that in a real setup one would need to handle more than a single `WebSocketSession`
- `TurboStreamSSEController.java` – a simple implementation of a `RequestController` that uses [SSE](https://developer.mozilla.org/en-US/docs/Web/API/Server-sent_events) to do the communication with the client.

## Showcased features

- Navigation with Turbo Drive is enbled (visible through the progress bar and the fact that `Originator` for any Request in the Dev-Network Panel is: `turbo.js`)
- Simple interaction of turbo-frames: only a part of a page is updated
- Deferred loading of content with turbo-frames
- interacting with other turbo-frames (trigger a request from a different part of the page than the one that is updated)
- breaking free from a turbo-frame (navigating from inside a frame in a way that the whole window is replaced)
- Using turbo-streams as a response to a normal form `POST` to update multiple elements on a page
- Using turbo-Streams over Websockets to live-update a page
- Using turbo-Streams over Websockets to live-update a page triggered through a Custom Element
- Using turbo-Streams over SSE to live-update a page

