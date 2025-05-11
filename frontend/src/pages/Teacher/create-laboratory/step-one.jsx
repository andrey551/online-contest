import React, {useState} from 'react';
import DatePicker from "react-datepicker";

import "react-datepicker/dist/react-datepicker.css";
import {setLaboratoryAbout} from "../../../hooks/modules/laboratory";
import {useDispatch, useSelector} from "react-redux";

const InfoForm = ({toNextPage}) => {
    const dispatch = useDispatch();
    const about = useSelector((state => state.laboratory.laboratoryToAdd.about))
    const [title, setTitle] = useState(about.title)
    const [tags, setTags] = useState(about.tags)
    const [deadline, setDeadline] = useState(about.deadline === null ? new Date() : about.deadline);
    const nextOnAction = () => {
        console.log({
            title: title,
            tags: tags,
            deadline: deadline
        })
        dispatch(setLaboratoryAbout({
            title: title,
            tags: tags,
            deadline: deadline
        }));
        toNextPage("pagetwo");
    }
    return (
        <>
            <div className={"info-form-container"}>
                <table>
                    <tbody>
                    <tr>
                        <td><label>Title</label></td>
                        <td><input
                            value = {title}
                            type={"text"}
                            onChange={e => setTitle( e.target.value)}/></td>
                    </tr>
                    <tr>
                        <td><lable>Tags</lable></td>
                        <td><input
                            value = {tags}
                            type={"text"}
                            onChange={e => setTags( e.target.value)}/> </td>
                    </tr>
                    <tr>
                        <td><label>Deadline</label></td>
                        <td><DatePicker
                                locale="ru"
                                selected={deadline}
                                onChange={(date) => setDeadline(date)}  showMonthYearDropdown/></td>
                    </tr>
                    </tbody>
                </table>
                <div className="step-control">
                    <input  type={"submit"} value={"Next"} onClick={() => nextOnAction()}/>
                </div>
            </div>
        </>
    )
}

export default InfoForm;