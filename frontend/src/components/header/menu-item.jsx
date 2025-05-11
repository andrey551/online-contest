import React from 'react';
import './header.css';

const MenuItem = ({ item }) => {
    return (
        <div className="menu-item">
            <img className="menu-item-icon" src={item.image} alt={item.content} />
            <span className="menu-item-text">{item.content}</span>
        </div>
    );
};

export default MenuItem;