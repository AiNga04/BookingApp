import type { PayloadAction } from "@reduxjs/toolkit";
import { createSlice } from "@reduxjs/toolkit";
import { useAppDispatch, useAppSelector } from "../hooks";
import type { RootState } from "../store";
import type { ICurrentUser, ICurrentUserState } from "./models";

const initialState: ICurrentUserState = {
  user: null,
  isLoading: false,
};

export const currentUserSlice = createSlice({
  name: "currentUser",
  initialState,
  reducers: {
    setCurrentUserAction: (state, action: PayloadAction<ICurrentUser>) => {
      state.user = action.payload;
    },
  },
});

const { setCurrentUserAction } = currentUserSlice.actions;

export const useGetCurrentUserState = (): ICurrentUserState =>
  useAppSelector((state: RootState) => state[currentUserSlice.name]);

export const useSetCurrentUserState = () => {
  const dispatch = useAppDispatch();
  return {
    setCurrentUser: (currentUser: ICurrentUser) =>
      dispatch(setCurrentUserAction(currentUser)),
  };
};

export default currentUserSlice;
