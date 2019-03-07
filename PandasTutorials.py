#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Mon Feb 18 20:15:39 2019

@author: rajeshnarayanarao
"""

import numpy as np
import pandas as pd

s = pd.Series([1, 3, 5, np.nan, 6, 8])
s
dates = pd.date_range('20130101', periods=6)
dates

df = pd.DataFrame(np.random.randn(6, 4), index=dates, columns=list('ABCD'))
df

df2 = pd.DataFrame({'A': 1.,
                        'B': pd.Timestamp('20130102'),
                        'C': pd.Series(1, index=list(range(4)), dtype='float32'),
                        'D': np.array([3] * 4, dtype='int32'),
                        'E': pd.Categorical(["test", "train", "test", "train"]),
                        'F': 'foo'})
df2
df2.dtypes
#df2.<TAB>  # noqa: E225, E999

df.head()
df.tail(3)
df.index
df.columns

#df.to_numpy()
#df2.to_numpy()

df.describe()
df.T # Transpose of a matrix

df.sort_index(axis=1, ascending=False)

df.sort_values(by='B')

df['A']

df[0:3]

df['20130102':'20130104']

df.loc[dates[0]]

df.loc[:, ['A', 'B']]

df.loc['20130102':'20130104', ['A', 'B']]

df.loc['20130102', ['A', 'B']]

df.loc[dates[0], 'A']

df.at[dates[0], 'A']

df.iloc[3]

df.iloc[3:5, 0:2]

df.iloc[[1, 2, 4], [0, 2]]

df.iloc[1:3, :]

df.iloc[:, 1:3]

df.iloc[1, 1]

df.iat[1, 1]

df[df.A > 0]

df[df > 0]

df2 = df.copy()
df2['E'] = ['one', 'one', 'two', 'three', 'four', 'three']
df2
df2[df2['E'].isin(['two', 'four'])]

s1 = pd.Series([1, 2, 3, 4, 5, 6], index=pd.date_range('20130102', periods=6))
df['F'] = s1
df.at[dates[0], 'A'] = 0
df.iat[0, 1] = 0
df.loc[:, 'D'] = np.array([5] * len(df))

