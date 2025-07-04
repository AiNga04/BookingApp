import type { User } from "oidc-client-ts";

export type ICurrentUser = User | null;

export interface ICurrentUserState {
  user: ICurrentUser;
  isLoading: boolean;
}
