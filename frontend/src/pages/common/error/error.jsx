// ErrorPage.js
import React from 'react';
import { useNavigate } from 'react-router-dom';
import './ErrorPage.css'; // Optional styling

const ErrorPage = ({ errorCode = 404, errorMessage = "Page Not Found" }) => {
    const navigate = useNavigate();

    return (
        <div className="error-page">
            <div className="error-content">
                <h1 className="error-code">{errorCode}</h1>
                <h2 className="error-message">{errorMessage}</h2>
                <p className="error-description">
                    Oops! The page you're looking for doesn't exist or another error occurred.
                </p>
                <button
                    className="home-button"
                    onClick={() => navigate('/error')}
                >
                    Go Back Home
                </button>
            </div>
        </div>
    );
};

export default ErrorPage;