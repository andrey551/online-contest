const KEYCLOAK_URL = "http://localhost:8080/realms/contest/protocol/openid-connect/auth";
const CLIENT_ID = "online-contest-frontend";
const REDIRECT_URI = window.location.origin;

export function checkAuth() {
    const token = localStorage.getItem('token');

    if (!token) {
        window.location.href = `${KEYCLOAK_URL}?client_id=${CLIENT_ID}&redirect_uri=${REDIRECT_URI}&response_type=code&scope=openid`;
    }
}
