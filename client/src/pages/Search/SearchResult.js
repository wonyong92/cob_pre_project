import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import MyButton from '../../components/MyButton';
import MyFooter from '../../components/MyFooter';
import Nav from '../../components/Nav';
import SearchListItem from '../../components/SearchListItem';

/* eslint-disable react/prop-types */
const SearchResult = ({ searchList, searchCount }) => {
  const navigate = useNavigate();
  return (
    <Container>
      <Top>
        <Nav />
        <Wrapper>
          <div className="title">
            <div className="top">
              <div>Search Results</div>
              <div className="top-right">
                <span>Advanced Search Tips</span>
                <MyButton
                  onClick={() => navigate('/questionwrite')}
                  text={'Ask Question'}
                  type={'blue'}
                />
              </div>
            </div>
            <div className="middle">
              <span>
                Results for how <br />
                Search options <strong>not deleted</strong>
              </span>
            </div>
            <div className="bottom">
              <span>{searchCount} Question results</span>
            </div>
          </div>
          <div className="list">
            {searchList.map((el, idx) => (
              <SearchListItem key={idx} data={el} />
            ))}
          </div>
        </Wrapper>
      </Top>
      <MyFooter />
    </Container>
  );
};

export default SearchResult;

const Container = styled.div`
  display: flex;
  flex-direction: column;
`;
const Top = styled.div`
  display: flex;
`;
const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  .title {
    width: 1070px;
    height: 190px;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    padding-left: 20px;
    padding-top: 20px;
    border-bottom: 1px solid #cccdce;
    .top {
      width: 1070px;
      display: flex;
      justify-content: space-between;
      div:first-child {
        font-size: 25px;
      }
    }
    .top-right {
      padding-right: 20px;
      span {
        color: #2d95ff;
        font-size: 14px;
        margin-right: 15px;
        cursor: pointer;
      }
      span:hover {
        color: #2d95ff;
      }
      button {
        width: 100px;
        height: 40px;
      }
    }
    .middle {
      font-size: 14px;
      color: gray;
    }
    .bottom {
      margin-bottom: 10px;
    }
  }
  .list {
    display: flex;
    flex-direction: column;
  }
`;
