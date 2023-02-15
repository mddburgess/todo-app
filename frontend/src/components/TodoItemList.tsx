import {useDeleteTodoItemMutation, useListTodoItemsQuery} from "../api/todoApi";
import TodoItemRow from "./TodoItemRow";
import ListGroup from "react-bootstrap/ListGroup";

const TodoItemList = () => {
    const todoItems = useListTodoItemsQuery()
    const [deleteTodoItem] = useDeleteTodoItemMutation()

    const onDelete = (id: number) => {
        deleteTodoItem({id})
    }

    return (
        <ListGroup className="mt-3 mb-5">
            {todoItems.data?.map(item => <TodoItemRow item={item} onDelete={onDelete}/>)}
        </ListGroup>
    )
}

export default TodoItemList;
