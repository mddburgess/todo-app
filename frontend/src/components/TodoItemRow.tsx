import {TodoItem} from "../api/todoApi";
import ListGroupItem from "react-bootstrap/ListGroupItem";
import {Trash3} from "react-bootstrap-icons";
import {Button, Col, Row} from "react-bootstrap";

interface Props {
    item: TodoItem,
    onDelete: (id: number) => void,
}

const TodoItemRow = ({item, onDelete}: Props) => {

    const onClick = () => onDelete(item.id)

    return (
        <ListGroupItem>
            <Row>
                <Col className="d-flex align-items-center">
                    {item.summary}
                </Col>
                <Col xs="auto" className="px-1">
                    <Button
                        variant="outline-danger"
                        className="d-flex align-items-center"
                        onClick={onClick}
                    >
                        <Trash3/>
                    </Button>
                </Col>
            </Row>
        </ListGroupItem>
    );
}

export default TodoItemRow;
