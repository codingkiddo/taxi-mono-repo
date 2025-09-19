# Keycloak Dev Realm (taxi)

- URL: http://localhost:8080
- Realm: `taxi`
- Users: `admin` / `admin`
- Clients: `gateway`, `rider-app`, `driver-app` (public clients)

## Getting a Token (Password Grant for dev)
```bash
curl -s -X POST \
  -d 'grant_type=password' \
  -d 'client_id=gateway' \
  -d 'username=admin' \
  -d 'password=admin' \
  http://localhost:8080/realms/taxi/protocol/openid-connect/token
```
Use the `access_token` as `Authorization: Bearer <token>` in Postman if you enable auth on routes later.
