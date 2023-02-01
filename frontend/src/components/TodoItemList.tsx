import {useListTodoItemsQuery} from "../api/todoApi";
import TodoItemRow from "./TodoItemRow";
import AddTodoItemRow from "./AddTodoItemRow";
import ListGroup from "react-bootstrap/ListGroup";

const TodoItemList = () => {
    const todoItems = useListTodoItemsQuery()

    return (
        <ListGroup>
            {todoItems.data?.map(item => <TodoItemRow item={item}/>)}
            <AddTodoItemRow/>
        </ListGroup>
    )
}

export default TodoItemList;
