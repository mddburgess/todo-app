import {useListTodoItemsQuery} from "../api/todoApi";

const TodoItemList = () => {
    const todoItems = useListTodoItemsQuery()

    return (
        <ul>
            {todoItems.data?.map(item => <li>{item.summary}</li>)}
        </ul>
    )
}

export default TodoItemList;
