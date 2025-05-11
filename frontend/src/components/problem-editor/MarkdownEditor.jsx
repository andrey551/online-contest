import React, {useState} from "react";
import {marked} from "marked";
import { Prism as SyntaxHighlighter } from "react-syntax-highlighter";
import { darcula } from "react-syntax-highlighter/dist/esm/styles/prism";

import './markdown-editor.css'

marked.setOptions({
    highlight: (code, language) => {
        return SyntaxHighlighter.highlight(code, { language, style: darcula }).value;
    },
});

const MarkdownEditor = () => {
    const [markdown, setMarkdown] = useState("")
    const [openPreview, setOpenPreview] = useState(false)

    const handleInputChange = (e) => {
        setMarkdown(e.target.value);
    };

    // Convert Markdown to HTML
    const getMarkdownText = () => {
        return { __html: marked(markdown) };
    };

    const onOpenPreview = () => {
        setOpenPreview(!openPreview)
    }

    return(
        <>
            <div className="editor-container">
                <div className={'editor-wrapper'}>
                    <textarea
                        id="editor"
                        value={markdown}
                        onChange={handleInputChange}
                        placeholder="Write your Markdown here..."
                    />
                    <button className={'control-preview-btn'}
                            onClick={onOpenPreview}>
                        <img className={'preview-icon'} src = {require("../../assets/website.png")} alt={'Preview'}/>
                    </button>
                </div>
                <div className={'editor-wrapper'}>
                    {openPreview ? <div id="preview" dangerouslySetInnerHTML={getMarkdownText()}/> : <></>}
                </div>
            </div>
        </>
    )
}

export default MarkdownEditor;