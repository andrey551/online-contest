#!/bin/bash
# Wait for Keycloak to be ready
until curl -f http://localhost:8080/health/ready; do sleep 5; done

# Get client secret and pass to Gateway
CLIENT_SECRET=$(/opt/keycloak/bin/kcadm.sh get clients/gateway-client/secret -r contest \
  --server http://localhost:8080 \
  --realm master \
  --user admin \
  --password admin | jq -r '.value')

# Export for Docker Compose
echo "KEYCLOAK_CLIENT_SECRET=$CLIENT_SECRET" > .env

# Keep container running
tail -f /dev/null