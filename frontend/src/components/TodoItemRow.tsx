import {TodoItem} from "../api/todoApi";
import ListGroupItem from "react-bootstrap/ListGroupItem";

interface Props {
    item: TodoItem
}

const TodoItemRow = ({item}: Props) => (
    <ListGroupItem>{item.summary}</ListGroupItem>
)

export default TodoItemRow;
