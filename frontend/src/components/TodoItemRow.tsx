import {TodoItem} from "../api/todoApi";

interface Props {
    item: TodoItem
}

const TodoItemRow = ({item}: Props) => (
    <li>{item.summary}</li>
)

export default TodoItemRow;
