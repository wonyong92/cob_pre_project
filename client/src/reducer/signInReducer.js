const initialState = {
  loginState: false,
  data: {
    userid: 'guest',
    memberId: -1,
  },
};

const signInReducer = (state = initialState, action) => {
  switch (action.type) {
    case 'CHECK_LOGIN':
      return { ...state, loginState: action.payload.msg };
    case 'USER_DATA':
      return { ...state, data: action.payload.data };
    case 'SIGN_OUT':
      return { ...state, loginState: action.payload.msg };
    default:
      return state;
  }
};
export default signInReducer;
