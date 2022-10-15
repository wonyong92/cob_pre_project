import { Link, useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import MyButton from '../../components/MyButton';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faStackOverflow } from '@fortawesome/fontawesome-free-brands';
import { useState } from 'react';
import axios from 'axios';
import { setLoginCookie, getLoginCookie } from '../../lib/cookie';
import { setSignState, setUserData } from '../../action/action';
import { useDispatch } from 'react-redux';
/* eslint-disable react/prop-types */
const Login = () => {
  const [userInfo, setUserInfo] = useState({
    id: '',
    password: '',
  });
  const [loginMsg, setLoginMsg] = useState(false);
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const getUserID = (e) => {
    setUserInfo({ ...userInfo, id: e.target.value });
  };
  const getUserPW = (e) => {
    setUserInfo({ ...userInfo, password: e.target.value });
  };
  const trySignIn = async () => {
    try {
      const res = await axios.post(`http://3.39.180.45:56178/login`, {
        id: userInfo.id,
        password: userInfo.password,
      });
      const data = res.data; // accesstoken, userid
      if (!data.token) {
        setLoginMsg(true);
      } else {
        //토큰
        setLoginCookie(data.token);
        localStorage.setItem('token', JSON.stringify(data.token));
        delete data.token;

        const res2 = await axios.get(
          'http://3.39.180.45:56178/DBtest/refreshToken',
          { headers: { Authorization: getLoginCookie() } }
        );
        const data2 = res2.data; // true
        dispatch(setSignState(data2.msg));
        delete data2.msg;
        dispatch(setUserData(data)); // data userid, memeberid
        // console.log(data);
        localStorage.setItem('userData', JSON.stringify(data));
        navigate('/');
        // console.log('login succed');
      }
    } catch (err) {
      if (err.response.status >= 400) {
        navigate('/');
      }
    }
  };
  const onPushEnter = (e) => {
    if (e.key === 'Enter') trySignIn();
  };
  return (
    <Container>
      <SigninBox>
        <FontAwesomeIcon icon={faStackOverflow} className="stacklogo" />
        <BtnWrapper>
          <MyButton
            text={'Log in with Google'}
            type={'default'}
            onClick={() => {}}
          />
        </BtnWrapper>
        <LoginBox>
          <span>Id</span>
          <input
            type="text"
            value={userInfo.id}
            onChange={getUserID}
            onKeyUp={onPushEnter}
          ></input>
          <span>Password</span>
          <input
            type="password"
            value={userInfo.password}
            onChange={getUserPW}
            onKeyUp={onPushEnter}
          ></input>
          <MyButton text={'Log in'} type={'blue'} onClick={trySignIn} />
          {loginMsg ? <div>Id와 비밀번호를 확인해주세요</div> : <div></div>}
        </LoginBox>
        <SignupBox>
          <p>
            Don&apos;t have an account?
            <Link to="/signup">
              <span className="signupbtn">Sign up</span>
            </Link>
          </p>
        </SignupBox>
      </SigninBox>
    </Container>
  );
};

export default Login;

const Container = styled.div`
  background-color: #f1f2f3;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  min-width: auto;
  width: 100%;
`;
const SigninBox = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 300px;
  height: 570px;
  .stacklogo {
    margin-bottom: 20px;
    font-size: 35px;
  }
`;
const LoginBox = styled.div`
  display: flex;
  flex-direction: column;
  background-color: white;
  justify-content: center;
  align-items: center;
  padding: 30px;
  width: 320px;
  height: 280px;
  border-radius: 10px;
  box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
  > span {
    font-size: 16px;
    font-weight: 700;
    margin-right: 230px;
    margin-bottom: 5px;
  }
  span:nth-of-type(2) {
    margin-right: 170px;
  }
  input {
    height: 35px;
    font-size: 16px;
    color: #363b3f;
    text-indent: 15px;
    margin-bottom: 20px;
    border: 1px solid #d5d7d9;
    border-radius: 5px;
    width: 250px;
  }
  input:focus {
    border: 1px solid cornflowerblue;
    border-radius: 2px;
    outline: none;
    box-shadow: 0 0 0 3px #cde9fe;
  }
  button {
    margin-top: 10px;
    font-size: 14px;
    width: 250px;
    height: 35px;
  }
`;
const SignupBox = styled.div`
  margin-top: 50px;
  display: flex;
  width: 240px;
  justify-content: space-around;
  p {
    font-size: 14px;
  }
  span {
    font-size: 14px;
    color: #3b79c8;
    margin-left: 10px;
  }
  a {
    text-decoration: none;
  }
`;
const BtnWrapper = styled.div`
  margin-bottom: 30px;
  position: relative;
  button {
    color: #44484c;
    border: 1px solid #d7d9dc;
    width: 320px;
    font-size: 14px;
    height: 37px;
  }
`;
