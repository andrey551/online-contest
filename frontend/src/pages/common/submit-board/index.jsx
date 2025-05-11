import LaboratoryForm from "./laboratory-form/laboratoryForm";
import './styles.css'
import SubmissionHistory from "./submission-form/SubmisisonForm";

const SubmitBoard = () => {
    return (
        <>
            <div className={'submit-board-container'}>
                <LaboratoryForm/>
                <SubmissionHistory/>
            </div>
        </>
    )
}

export default SubmitBoard;