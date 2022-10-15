import { Fragment } from 'react';
import styled from 'styled-components';

const StyledWidget = styled.div`
  display: flex;
  flex-direction: column;
  background-color: white;
  justify-content: center;
  align-items: center;
  margin-left: 30px;
  padding-top: 0px;
  padding-bottom: 0px;
  width: 314px;
  height: 587.17px;
  border-radius: 10px;
  position: center;
  box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;

  .widget-header {
    display: flex;
    align-items: center;
    width: 100%;
    background-color: #f1f2f3;
    border-top: 1px solid #e4e6e8;
    font-weight: normal;
    border-top-right-radius: 3px;
    border-top-left-radius: 3px;
    padding-bottom: 10px;
    padding-top: 10px;
  }

  .widget-content {
    width: 314px;
    height: 587.17px;
    display: block !important;
    border-top: 1px solid #404345;
    color: #242729;
    border-bottom-right-radius: 3px;
    border-bottom-left-radius: 3px;
    padding: 10px 10px 10px;
    list-style: none;

    .summary {
      margin-bottom: 16px;

      .sec1 {
        margin-bottom: 13px;
      }
      .sec2 {
        margin-bottom: 13px;
      }
    }

    .step-section {
      border-radius: 3px;

      .step {
        border-bottom: 1px solid lightgray;
        list-style: none;

        button {
          display: flex;
          width: 100% !important;
          cursor: pointer;
          padding: 13px 13px 14px;
          border: none;
          outline: none;
          font: unset;
          border-radius: 0;
          color: unset;
          background: none;
          box-shadow: none;

          .step-cell {
            display: flex;
            flex: 1 auto;
            align-items: center;

            div {
              word-spacing: normal;

              img {
                width: 16px;
                height: 16px;
              }
            }
          }
          span {
            font-weight: 700 !important;
            margin-left: 12px !important;
            cursor: pointer;
          }

          .inst {
            margin-left: 17px !important;
            margin-right: 17px !important;
            display: flex;
            align-items: flex-start;

            .inst-content {
              padding-bottom: 12px !important;
              margin-bottom: 0;

              ul {
                padding-left: 10px;

                p {
                  clear: both;
                  margin-bottom: 13px;
                  margin-top: 0;
                  padding-left: 40px;
                }

                .except {
                  margin-bottom: 0;
                }
              }
            }
          }
        }
      }
    }
  }
`;

const Widget = () => {
  return (
    <Fragment>
      <StyledWidget>
        <h4 className="widget-header">Step 1: Draft your question</h4>
        <div className="widget-content">
          <div className="summary">
            <p className="sec1">
              The community is here to help you with specific coding, algorithm,
              or language problems.
            </p>
            <p className="sec2">Avoid asking opinion-based questions.</p>
          </div>
          <ol className="step-section">
            <li className="step">
              <button>
                <div className="step-cell">
                  <div>
                    <img
                      src="https://cdn.sstatic.net/Img/list-1.svg?v=e8dd475ba207"
                      width="16"
                      height="16"
                      alt="1.5"
                    />
                  </div>
                  <span>Summarize the problem</span>
                </div>
              </button>
              <div className="inst">
                <div className="inst-content">
                  <ul>
                    <li>
                      <p>Include details about your goal</p>
                    </li>
                    <li>
                      <p>Describe expected and actual results</p>
                    </li>
                    <li>
                      <p className="except">Include any error messages</p>
                    </li>
                  </ul>
                </div>
              </div>
            </li>
            <li className="step">
              <button>
                <div className="step-cell">
                  <div>
                    <img
                      src="https://cdn.sstatic.net/Img/list-2.svg?v=9382fc2c3631"
                      width="16"
                      height="16"
                      alt="2."
                    />
                  </div>
                  <span>Summarize the problem</span>
                </div>
              </button>
              <div className="inst">
                <div className="inst-content">
                  <p className="step2">
                    Show what you&apos;ve tried and tell us what you found (on
                    this site or elsewhere) and why it didn&apos;t meet your
                    needs. You can get better answers when you provide research.
                  </p>
                </div>
              </div>
            </li>
            <li
              style={{
                borderBottomRightRadius: '3px',
                borderBottomLeftRadius: '3px',
              }}
              className="step except-step"
            >
              <button>
                <div className="step-cell">
                  <div>
                    <img
                      src="https://cdn.sstatic.net/Img/list-3.svg?v=323a95564232"
                      width="16"
                      height="16"
                      alt="3."
                    />
                  </div>
                  <span>Summarize the problem</span>
                </div>
              </button>
              <div className="inst">
                <div className="inst-content">
                  <p className="step3">
                    When appropriate, share the minimum amount of code others
                    need to reproduce your problem (also called a minimum,
                    reproducible example)
                  </p>
                </div>
              </div>
            </li>
          </ol>
        </div>
      </StyledWidget>
    </Fragment>
  );
};

export default Widget;
