import React from 'react';
import './header.css';
import Logo from "./logo";
import Menu from "./menu";

const Header = () => {
    return (
        <header className="header-container">
            <div className="left-header">
                <Logo />
            </div>
            <div className="right-header">
                <Menu />
            </div>
        </header>
    );
};

export default Header;