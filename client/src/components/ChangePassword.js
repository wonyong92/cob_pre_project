import styled from 'styled-components';
import MyButton from './MyButton';
import axios from 'axios';
import { useState } from 'react';
import { getLoginCookie } from '../lib/cookie';
/* eslint-disable react/prop-types */
const ChangePassWord = ({ userId }) => {
  const [currentPW, setCurrentPW] = useState('');
  const [newPW, setNewPW] = useState('');
  const [newRePW, setNewRePW] = useState('');
  const [checkMsg1, setCheck1] = useState();
  const [checkMsg2, setCheck2] = useState();
  const [checkMsg3, setCheck3] = useState();

  const isValidPassword = (str) => {
    let regExp =
      /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;
    return regExp.test(str);
  };
  const onCurrentPW = (e) => {
    setCurrentPW(e.target.value);
    isValidPassword(e.target.value)
      ? setCheck1(<p>비밀번호가 조건에 만족합니다</p>)
      : setCheck1(
          <p>8 ~ 16자 사이의 영문, 숫자, 특수문자를 최소 한가지씩 조합하세요</p>
        );
  };
  const onNewPW = (e) => {
    setNewPW(e.target.value);
    if (currentPW === e.target.value) {
      setCheck2(<p>현재 비밀번호와 같습니다</p>);
    } else if (
      currentPW !== e.target.value &&
      isValidPassword(e.target.value)
    ) {
      setCheck2(<p>비밀번호가 조건에 만족합니다</p>);
    } else if (
      currentPW !== e.target.value &&
      !isValidPassword(e.target.value)
    ) {
      setCheck2(
        <p>8 ~ 16자 사이의 영문, 숫자, 특수문자를 최소 한가지씩 조합하세요</p>
      );
    }
  };
  const onNewRePW = (e) => {
    setNewRePW(e.target.value);
    if (currentPW === e.target.value && newPW === e.target.value) {
      setCheck3(<p>현재 비밀번호와 같아 비밀번호가 변경되지 않습니다</p>);
    } else if (newPW === e.target.value) {
      setCheck3(<p>비밀번호가 일치합니다</p>);
    } else {
      setCheck3(<p>비밀번호가 일치하지 않습니다</p>);
    }
  };
  const onSubmitNewPW = (e) => {
    e.preventDefault();
    if (currentPW === newPW && newPW === newRePW) {
      return;
    } else if (!currentPW || !newPW || !newRePW) {
      return;
    }
    if (currentPW !== newPW && newPW === newRePW) {
      axios
        .post(
          `http://3.39.180.45:56178/DBtest/updatePassword?member_id=${userId}`,
          {
            newer: newRePW,
            elder: currentPW,
          },
          { headers: { Authorization: getLoginCookie() } }
        )
        .catch(() => {});
    }
  };
  return (
    <Container>
      <form>
        <div>
          <span>Current password</span>
          <input
            type="password"
            value={currentPW}
            onChange={onCurrentPW}
          ></input>
          {checkMsg1}
        </div>
        <div>
          <span>New password</span>
          <input type="password" value={newPW} onChange={onNewPW}></input>
          {checkMsg2}
        </div>
        <div>
          <span>New password (again)</span>
          <input type="password" value={newRePW} onChange={onNewRePW}></input>
          {checkMsg3}
        </div>
        <p>
          Passwords must contain at least eight characters, including at least 1
          letter and 1 number.
        </p>
        <MyButton
          text={'Change PassWord'}
          type={'blue'}
          onClick={(e) => onSubmitNewPW(e)}
        />
      </form>
    </Container>
  );
};
export default ChangePassWord;

const Container = styled.div`
  display: flex;
  flex-direction: column;
  margin-top: 20px;
  margin-bottom: 10px;
  padding-top: 20px;
  padding-left: 20px;
  border: 1px solid #d0d4d7;
  border-radius: 5px;
  min-width: 350px;
  max-width: 1040px;
  form {
    div {
      display: flex;
      flex-direction: column;
    }
  }
  span {
    margin-top: 10px;
    font-size: 18px;
    font-weight: 700;
    margin-right: 180px;
    margin-bottom: 5px;
    white-space: nowrap;
  }
  input {
    height: 35px;
    min-width: 350px;
    font-size: 14px;
    color: #363b3f;
    text-indent: 15px;
    border: 1px solid #d5d7d9;
    border-radius: 5px;
    width: 280px;
  }
  input:focus {
    border: 1px solid cornflowerblue;
    border-radius: 2px;
    outline: none;
    box-shadow: 0 0 0 3px #cde9fe;
  }
  button {
    font-size: 14px;
    min-width: 350px;
    height: 40px;
    margin: 20px 0;
  }
  p {
    margin-left: 3px;
    margin-top: 5px;
    margin-bottom: 10px;
    color: #7f868d;
    font-size: 14px;
  }
`;
