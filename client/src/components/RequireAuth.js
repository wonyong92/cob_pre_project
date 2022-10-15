import { useSelector } from 'react-redux';
import { Navigate, useLocation } from 'react-router-dom';
/* eslint-disable react/prop-types */
const RequireAuth = ({ children, option }) => {
  const state = useSelector((state) => state.signInReducer);
  const location = useLocation();

  // option true = 로그인시에만 가능
  // option false = 로그아웃시에만 가능
  // option null = 둘다 가능

  if (option === null) {
    return children;
  } else if (option) {
    if (state.loginState) return children;
    else {
      return <Navigate to={'/'} state={location}></Navigate>;
    }
  } else {
    if (state.loginState) {
      return <Navigate to={'/'} state={location}></Navigate>;
    } else return children;
  }
};

export default RequireAuth;
