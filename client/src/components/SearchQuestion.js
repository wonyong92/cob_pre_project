/* eslint-disable react/prop-types */
import { useState } from 'react';
import styled from 'styled-components';
const SearchQuestion = ({ handleKeywordKeyword }) => {
  const [keyword, setkeyword] = useState('');
  const onSubmitKeyword = (e) => {
    if (e.key === 'Enter') {
      handleKeywordKeyword(keyword);
    }
  };
  return (
    <Container>
      <input
        value={keyword}
        onChange={(e) => {
          setkeyword(e.target.value);
        }}
        onKeyUp={(e) => {
          onSubmitKeyword(e);
        }}
        type="text"
        placeholder="Search..."
      />
    </Container>
  );
};
export default SearchQuestion;

const Container = styled.div`
  width: 100%;
  input {
    width: 100%;
    height: 30px;
    font-size: 14px;
    color: #363b3f;
    text-indent: 35px;
    border: 1px solid #8a939b;
  }
  input:focus {
    border: 1px solid cornflowerblue;
    border-radius: 2px;
    outline: none;
    box-shadow: 0 0 0 3px #cde9fe;
  }
`;
