import React from 'react';
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';
import './styles.css';

const ManualCalendar = () => {
    return(
        <>
            <Calendar locale={"ru-RU"} />
        </>
    )
}

export default ManualCalendar;