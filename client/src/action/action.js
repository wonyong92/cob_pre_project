export const setSignState = (msg) => {
  return {
    type: 'CHECK_LOGIN',
    payload: {
      msg: msg,
    },
  };
};

export const setUserData = (data) => {
  return {
    type: 'USER_DATA',
    payload: {
      data: data,
    },
  };
};

export const trySignout = () => {
  return {
    type: 'SIGN_OUT',
    payload: {
      msg: false,
    },
  };
};
