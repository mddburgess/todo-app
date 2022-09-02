import {ApiProvider} from "@reduxjs/toolkit/dist/query/react";
import TodoItemList from "./components/TodoItemList";
import {api} from "./store/api";

export const App = () => (
    <ApiProvider api={api}>
        <h1>Hello world!</h1>
        <TodoItemList/>
    </ApiProvider>
)
