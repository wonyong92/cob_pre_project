import styled from 'styled-components';
import { useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCaretUp, faCaretDown } from '@fortawesome/free-solid-svg-icons';
/* eslint-disable react/prop-types */
const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 10px;

  margin: 10px;
  .arrow {
    font-size: 3rem;
    color: #babfc4;
    cursor: pointer;
  }
  span {
    color: #abb0b5;
    font-size: 20px;
  }
`;
const AnswerVote = ({ votes, onIncreaseVote, onDecreaseVote }) => {
  const [count, setCount] = useState(0);
  const onPlus = () => {
    if (count === 0) {
      onIncreaseVote();
      setCount(count + 1); // +1
    } else if (count === 1) {
      return;
    } else if (count === -1) {
      onIncreaseVote();
      setCount(count + 1); // 0
    }
  };
  const onMinus = () => {
    if (count === 0) {
      onDecreaseVote();
      setCount(count - 1); // -1
    } else if (count === -1) {
      return;
    } else if (count === 1) {
      onDecreaseVote();
      setCount(count - 1); // 0
    }
  };
  return (
    <Container>
      <FontAwesomeIcon icon={faCaretUp} onClick={onPlus} className="arrow" />
      <span>{votes}</span>
      <FontAwesomeIcon icon={faCaretDown} onClick={onMinus} className="arrow" />
    </Container>
  );
};
export default AnswerVote;
