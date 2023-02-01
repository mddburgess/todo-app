import {CreateTodoItemRequest} from "../api/todoApi";
import {Field, Form, Formik} from "formik";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import FormControl from "react-bootstrap/FormControl";

interface Props {
    initialState: CreateTodoItemRequest;
    onSave: (todoItem: CreateTodoItemRequest) => void;
}

const TodoItemForm = ({initialState, onSave}: Props) => (
    <Formik initialValues={initialState} onSubmit={onSave}>
        <Form>
            <Row>
                <Col>
                    <Field
                        name="todoItem.summary"
                        type="text"
                        as={FormControl}
                        size="sm"
                        placeholder="Add a todo item..."
                    />
                </Col>
            </Row>

        </Form>
    </Formik>
)

export default TodoItemForm;
