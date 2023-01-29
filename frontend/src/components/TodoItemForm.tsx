import {CreateTodoItemRequest} from "../api/todoApi";
import {Field, Form, Formik} from "formik";

interface Props {
    initialState: CreateTodoItemRequest;
    onSave: (todoItem: CreateTodoItemRequest) => void;
}

const TodoItemForm = ({initialState, onSave}: Props) => (
    <Formik initialValues={initialState} onSubmit={onSave}>
        <Form>
            <Field name="todoItem.summary" type="text"/>
        </Form>
    </Formik>
)

export default TodoItemForm;
