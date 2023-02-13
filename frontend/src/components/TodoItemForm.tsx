import {CreateTodoItemRequest} from "../api/todoApi";
import {Field, Form, Formik} from "formik";
import FormControl from "react-bootstrap/FormControl";
import {Button, InputGroup} from "react-bootstrap";
import {PencilFill} from "react-bootstrap-icons";

interface Props {
    initialState: CreateTodoItemRequest;
    onSubmit: (todoItem: CreateTodoItemRequest) => void;
}

const TodoItemForm = ({initialState, onSubmit}: Props) => (
    <Formik initialValues={initialState} onSubmit={onSubmit}>
        <Form>
            <InputGroup>
                <Field
                    name="todoItem.summary"
                    type="text"
                    as={FormControl}
                    placeholder="Add a todo item..."
                />
                <Button type="submit" className="d-flex align-items-center">
                    <PencilFill/>
                </Button>
            </InputGroup>
        </Form>
    </Formik>
)

export default TodoItemForm;
