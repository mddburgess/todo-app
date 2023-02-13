import {useListTodoItemsQuery} from "../api/todoApi";
import TodoItemRow from "./TodoItemRow";
import ListGroup from "react-bootstrap/ListGroup";

const TodoItemList = () => {
    const todoItems = useListTodoItemsQuery()

    return (
        <ListGroup className="mt-3 mb-5">
            {todoItems.data?.map(item => <TodoItemRow item={item}/>)}
        </ListGroup>
    )
}

export default TodoItemList;
