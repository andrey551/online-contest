import React from 'react';
import './header.css';

const Logo = () => {
    return (
        <div className="logo-container">
            <img className="logo" src={require('../../assets/img.png')} alt="logo"/>
        </div>
    );
};

export default Logo;