package com.team23.PreProject.answer.dto;

import com.team23.PreProject.answer.entity.answer;

import java.util.Comparator;

public class comp implements Comparator<answer>
{    @Override
public int compare(answer a1, answer a2)
{        if (a1.getAnswerId() > a2.getAnswerId())
{            return -1;        }
else if (a1.getAnswerId() < a2.getAnswerId())
{            return 1;        }        return 0;    }}