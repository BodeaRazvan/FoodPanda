import { createSlice } from '@reduxjs/toolkit'

export const authSlice = createSlice({
    name: 'Authentication',
    initialState: {
        toke:'',
        user:null
    },
    reducers: {
        login:(state,action)=>{
            state.token = action.payload.token
            state.user = action.payload.user
        },
        logout:(state)=>{
            state.token = null
            state.user = null
        },
    },
})

// Action creators are generated for each case reducer function
export const {login,logout} = authSlice.actions

export default authSlice.reducer