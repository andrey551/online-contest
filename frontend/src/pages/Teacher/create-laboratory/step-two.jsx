import React, {useState} from "react";
import MarkdownEditor from '@uiw/react-markdown-editor';

import './styles.css'
import {useDispatch, useSelector} from "react-redux";
import {setDescription} from "../../../hooks/modules/laboratory";

const DescriptionEditor = ({toNextPage}) => {
    const dispatch = useDispatch();
    let markdown = useSelector((state => state.laboratory.laboratoryToAdd.description))
    function setMarkdown(value) {
        markdown = value
    }

    const nextOnAction = () => {
        dispatch(setDescription(markdown));
        toNextPage("pagethree")
    }
    return(
        <>
        <div className={"markdown-editor-container"}>
            <div className={"editor-area"}>
                <MarkdownEditor
                    value={markdown}
                    height={"30em"}
                    onChange={(value, viewUpdate) => setMarkdown(value)}
                />
            </div>
            <div className = "step-control">
                <input type={"submit"} value={"Next"} onClick={() => nextOnAction()}/>
            </div>
        </div>
</>
)
}

export default DescriptionEditor;