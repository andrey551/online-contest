import { useEffect } from 'react';
import { useHistory } from 'react-router-dom';

const OAuth2Callback = () => {
    const history = useHistory();

    useEffect(() => {
        // Lấy mã code từ URL
        const urlParams = new URLSearchParams(window.location.search);
        const code = urlParams.get('code');

        if (code) {
            // Gửi yêu cầu đến backend hoặc Keycloak để đổi mã code lấy access token
            fetch('http://localhost:8086/realms/contest/protocol/openid-connect/token', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: new URLSearchParams({
                    grant_type: 'authorization_code',
                    code: code,
                    redirect_uri: 'http://localhost:3000/login/oauth2/code/keycloak',
                    client_id: 'online-contest-frontend',
                    client_secret: 'CFlzJldwrpjE54LmlwHd7sURVg2kCPD2',
                }),
            })
                .then(response => response.json())
                .then(data => {
                    if (data.access_token) {
                        // Lưu token vào localStorage
                        localStorage.setItem('token', data.access_token);
                        // Điều hướng đến trang chính hoặc một trang khác sau khi lưu token
                        history.push('/home'); // Thay đổi route tùy theo yêu cầu
                    } else {
                        console.error('Không nhận được token từ Keycloak');
                    }
                })
                .catch(error => {
                    console.error('Có lỗi khi gọi API:', error);
                });
        }
    }, [history]);

    return (
        <div>Processing...</div>
    );
};

export default OAuth2Callback;
