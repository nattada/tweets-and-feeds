import { useState, useEffect } from "react";
import TweetList from "../notifications/TweetList";
import classes from "./TwitterContent.module.css";

function TwitterContent() {
  const [isLoading, setIsLoading] = useState(true);
  const [hasError, setHasError] = useState(false);

  const [loadedTweets, setLoadedTweets] = useState([]);

  useEffect(() => {
    setIsLoading(true);
    fetch("/twitter/seachTweets?text=something", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        "Authorization":
          "AAAAAAAAAAAAAAAAAAAAAOc3SQEAAAAAfjH%2FUkbUOoBFE%2F22be3bV5hPGRg%3DQAwkuet2GlZIf9b54zpYafCyHkYFGhJOAjggPMbzBf9qfE5Z78",
      },
    })
      .then(CheckError)
      .then((data) => {
        const tweets = [];
        for (const key in data) {
          const item = {
            id: key,
            ...data[key],
          };
          tweets.push(item);
        }
        setIsLoading(false);
        setLoadedTweets(tweets);
      })
      .catch(function () {
        setIsLoading(false);
        setHasError(true);
      });
  }, []);

  function CheckError(response) {
    if (response.status >= 200 && response.status <= 299) {
      return response.json();
    } else {
      throw Error(response.statusText);
    }
  }

  if (isLoading) {
    return (
      <section>
        <p>Loading...</p>
      </section>
    );
  }

  return (
      !hasError && (
        <div className= {classes.twitter}>
      <div className={classes.speech__bubble}><h1>Twitter: Who's talking abount Fanatics?</h1></div>
     
      <div >
        <TweetList allTweets={loadedTweets} />
      </div>
      </div>
      )
  );
}

export default TwitterContent;
