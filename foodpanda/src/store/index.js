import { configureStore } from '@reduxjs/toolkit'
import auth from './auth'
import {useSelector} from "react-redux";

export default configureStore({
    reducer: {
        Authentication: auth
    },
})

export const useAuth = () => useSelector((state) => state.Authentication)