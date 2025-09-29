## TODO

This project starts as a simple URL shortener built with **Java** and **Spring Boot**.  
It can be extended further without major architectural changes. Possible improvements include:

- **Redis Caching** — Cache frequently used short URLs to reduce database lookups.
- **Rate Limiting** — Prevent abuse by limiting the number of requests per user or per IP.
- **Custom Aliases** — Allow users to define their own short links (e.g., `localhost:8000/my-link`).
- **External Security Integration** — Support OAuth2, LDAP, or other security systems for authentication.
- **GraalVM Native Builds/Docker** — Compile into native executables to reduce startup time and runtime overhead.  
