import { combineReducers } from "@reduxjs/toolkit";
import currentUserSlice from "./currentUserSlice/currentUserSlice";

const rootReducer = combineReducers({
  [currentUserSlice.name]: currentUserSlice.reducer,
});

export default rootReducer;
