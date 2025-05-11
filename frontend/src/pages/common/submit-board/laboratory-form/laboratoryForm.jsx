import React, { useState } from 'react';
import ReactMarkdown from 'react-markdown';
import { FaCloudUploadAlt } from 'react-icons/fa';
import './styles.css';
import {useDispatch, useSelector} from "react-redux";
import remarkGfm from "remark-gfm";
import {sendSolutionEvent, setSolution} from "../../../../hooks/modules/submission";  // Đảm bảo đã import file CSS

export default function LaboratoryForm() {
    const dispatch = useDispatch();

    const markdown = useSelector((state => state.crtState.crtLaboratory.description));
    const selectedFile = useSelector((state => state.submission.solution))

    const handleFileChange = (e) => {
        if (e.target.files && e.target.files.length > 0) {
            dispatch(setSolution(e.target.files[0]))

        }
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (selectedFile) {
            console.log('File submitted:', selectedFile.name);
            dispatch(sendSolutionEvent());
        }
    };

    return (
        <div className="container">
            {/* Markdown Display */}
            <div className="markdown-container">
                <ReactMarkdown remarkPlugins={[remarkGfm]}>
                    {markdown}
                </ReactMarkdown>
            </div>

            {/* Upload Form */}
            <form className="upload-form" onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="file" className="label">Select file:</label>
                    <div className="file-upload-container">
                        {/* Hidden input file */}
                        <input
                            type="file"
                            id="file"
                            onChange={handleFileChange}
                            className="file-input"
                        />
                        {/* Custom file button with icon */}
                        <label htmlFor="file" className="file-btn">
                            <FaCloudUploadAlt className="file-icon"/>
                            <span>Select file</span>
                        </label>
                    </div>
                </div>

                <button type="submit" className="submit-btn">
                    Submit
                </button>

                {selectedFile && (
                    <p className="file-name">Selected: {selectedFile.name}</p>
                )}
            </form>

        </div>
    );
}
