import styled from 'styled-components';

export const Container = styled.div`
  background-color: #f1f2f3;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  min-width: auto;
  width: 100%;
  .msgContainer {
    margin-right: 40px;
    height: 300px;
  }
  .title {
    font-size: 25px;
    margin-bottom: 10px;
  }
  .sentence {
    font-size: 16px;
    margin-left: 10px;
    color: #4c4e51;
  }
  .footer {
    font-size: 14px;
    color: #6c757e;
  }
  .icon {
    color: #2a89fe;
  }
  .msgBox {
    display: flex;
    flex-direction: column;
    height: 200px;
    justify-content: space-around;
  }
`;

export const SignupContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 700px;
  .btnWrapper {
    margin-bottom: 30px;
    position: relative;
    button {
      color: #44484c;
      border: 1px solid #d7d9dc;
      width: 315px;
      height: 38px;
      font-size: 14px;
    }
  }
  .loginWrapper {
    margin-top: 50px;
    display: flex;
    width: 250px;
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
  }
`;

export const SignupBox = styled.div`
  display: flex;
  flex-direction: column;
  background-color: white;
  align-items: center;
  padding: 20px;
  width: 330px;
  height: 450px;
  border-radius: 10px;
  box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
  position: relative;

  button:first-of-type {
    width: 58px;
    height: 30px;
    position: absolute;
    top: 29px;
    left: 248px;
    font-size: 12px;
    border: none;
  }
  span {
    font-size: 16px;
    font-weight: 700;
    text-align: left;
    margin-right: 260px;
    margin-top: 15px;
    margin-bottom: 5px;
  }
  span:nth-of-type(2) {
    margin-right: 210px;
  }
  span:nth-of-type(3) {
    margin-right: 180px;
    width: 100px;
    white-space: nowrap;
  }
  input {
    height: 30px;
    font-size: 14px;
    color: #363b3f;
    text-indent: 10px;
    border: 1px solid #d5d7d9;
    border-radius: 5px;
    width: 280px;
    margin-bottom: 5px;
  }
  input:focus {
    border: 1px solid cornflowerblue;
    border-radius: 2px;
    outline: none;
    box-shadow: 0 0 0 3px #cde9fe;
  }
  button {
    font-size: 14px;
    width: 280px;
    margin: 30px 0;
  }
  p {
    color: #7f868d;
    font-size: 14px;
    width: 280px;
  }
`;
