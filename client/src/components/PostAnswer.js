import axios from 'axios';
import { useState } from 'react';
import styled from 'styled-components';
import { getLoginCookie } from '../lib/cookie';
import MyButton from './MyButton';
import { useSelector } from 'react-redux';
/* eslint-disable react/prop-types */
const PostAnswer = ({ pid, getAnswerAnswer }) => {
  const [content, setContent] = useState('');
  const state = useSelector((state) => state.signInReducer);
  // const userData = JSON.parse(localStorage.getItem('userData'));
  let memberId = state.data.memberId;
  const submitAnswer = () => {
    if (content === '') {
      return;
    } else {
      axios
        .post(
          `http://3.39.180.45:56178/DBtest/createAnswer/`,
          {
            post_id: pid,
            member_id: memberId,
            content: content,
          },
          { headers: { Authorization: getLoginCookie() } }
        )
        .then(() => {
          getAnswerAnswer();
        });
    }
  };

  return (
    <Container>
      <h3>Your Answer</h3>
      <textarea
        value={content}
        onChange={(e) => setContent(e.target.value)}
      ></textarea>
      <MyButton
        text={'Post Your Answer'}
        type={'blue'}
        onClick={state.loginState ? submitAnswer : null}
      />
      <p>
        Not the answer you&apos;re looking for? Browse other relative questions
        or ask your own question.
      </p>
    </Container>
  );
};
export default PostAnswer;
const Container = styled.div`
  display: flex;
  flex-direction: column;
  margin-left: 30px;
  margin-bottom: 30px;
  width: 1050px;
  h3 {
    font-weight: 500;
    margin-bottom: 15px;
  }
  textarea {
    min-height: 200px;
    font-size: 14px;
    color: #363b3f;
    text-indent: 10px;
    border: 1px solid #8a939b;
    margin-bottom: 15px;
    border-radius: 5px;
    padding-top: 5px;
  }
  textarea:focus {
    border: 1px solid cornflowerblue;
    border-radius: 2px;
    outline: none;
    box-shadow: 0 0 0 3px #cde9fe;
  }
  button {
    margin-top: 10px;
    height: 45px;
    width: 150px;
    font-size: 16px;
    margin-bottom: 15px;
  }
`;
